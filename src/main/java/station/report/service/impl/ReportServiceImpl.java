package station.report.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import station.report.pojo.Report;
import station.report.respository.ReportRespository;
import station.report.service.ReportService;
import station.users.pojo.Users;
import station.users.repository.UsersRepository;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private ReportRespository reportRespository;
	
	@Override
	public void export(HttpServletRequest request,HttpServletResponse response) {
		//获取数据
        //List<PageData> list = reportService.bookList(page);

        //excel标题
   String[] title = {"车票数量","总费用","车牌号","维修费用","维修日期"};

      //excel文件名
   String fileName = request.getParameter("type")+System.currentTimeMillis()+".xls";

//sheet名
   String sheetName = "财务报表";
   
   String[][] content =new String[0][0];
//   for (int i = 0; i < list.size(); i++) {
//         content[i] = new String[title.length];
//         PageData obj = list.get(i);
//         content[i][0] = obj.get("stuName").tostring();
//         content[i][1] = obj.get("stuSex").tostring();
//         content[i][2] = obj.get("stuAge").tostring();
//         content[i][3] = obj.get("stuSchoolName").tostring();
//         content[i][4] = obj.get("stuClassName").tostring();
//　　　}

   //创建HSSFWorkbook 
   HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

   //响应到客户端
  try {
 	 this.setResponseHeader(response, fileName);
 	 ByteArrayOutputStream  byteArrayOutputStream= new ByteArrayOutputStream();
 	 wb.write(byteArrayOutputStream);
	  OutputStream os = response.getOutputStream();
	  
	   	byteArrayOutputStream.writeTo(os);
	    byteArrayOutputStream.close();
	  os.flush();
	  
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	}
	
	

	public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/vnd.ms-excel;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



	@Override
	public void addreport(MultipartFile file,String type) throws IOException {
		//String path=this.getClass().getResource("").getPath();
		 File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
		String dir=courseFile.replace('\\', '/')+"/src/main/resources/static/report/";
		
		String filename=file.getOriginalFilename();
		
		//创建要保存文件的路径
       File dirFile = new File(dir+filename);
       if (!dirFile.exists()){
           dirFile.createNewFile();
       }
       try {
           //将文件写入创建的路径
           file.transferTo(dirFile);
           System.out.println("文件保存成功~");
       } catch (IOException e) {
           e.printStackTrace();
       }
		
       Report report=new Report();
       
       Users user=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer user_id=user.getUserid();
       Users users =this.usersRepository.findOne(user_id);
       
       report.setUsers(users);
       report.setReport_name(filename);
       report.setType(type);
       
       SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd"); 
		String strdate=format.format(new Date());
		try {
			Date date=format.parse(strdate);
			report.setReport_date(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.reportRespository.save(report);
	}



	@Override
	public List<Report> showreport() {
		
		return this.reportRespository.findAll();
	}



	@Override
	public void download(HttpServletRequest request, HttpServletResponse response, Integer report_id) throws IOException {
		Report report =this.reportRespository.findOne(report_id);
		String filename =report.getReport_name();
	
		File directory = new File("");// 参数为空
	        String courseFile = directory.getCanonicalPath();
		String path=courseFile.replace('\\', '/')+"/src/main/resources/static/report";
		
		
		File file=new File(path, filename);
		
		
		if(file.exists()) {
			this.setResponseHeader(response, filename);

			OutputStream out = response.getOutputStream();
            FileInputStream in = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }
            

            in.close();
            out.flush();
            System.out.println("下载成功: " + filename );
           
				    
		}
	
		 
		
	}



	@Override
	public List<Report> conditionalquery(Integer report_id, String type, String report_name) {
		Specification<Report> spec=new Specification<Report>() {

			public Predicate toPredicate(Root<Report> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list=new ArrayList<Predicate>();
				if(report_id!=null)
					list.add(cb.equal(root.get("report_id"),report_id));
				if(type!=null)
					list.add(cb.equal(root.get("type"), type));
				if(report_name!=null&&!report_name.isEmpty())
					list.add(cb.like(root.get("report_name"), "%"+report_name+"%"));
				Predicate[] arr=new Predicate[list.size()];
				return cb.and(list.toArray(arr));
			}
			
		};
		
		List<Report> list=this.reportRespository.findAll(spec);
		return list;
	}



	@Override
	public void delete(Integer report_id) throws IOException {
		Report report =this.reportRespository.findOne(report_id);
		String filename=report.getReport_name();
		
		File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        String path=courseFile.replace('\\', '/')+"/src/main/resources/static/report";
	
        File file=new File(path, filename);
        if(file.exists()) {
        	Files.delete(file.toPath());
        }
        this.reportRespository.delete(report_id);
        
	}



	@Override
	public void update(MultipartFile file,Integer report_id) throws IOException {
		//String path=this.getClass().getResource("").getPath();
		 File directory = new File("");// 参数为空
       String courseFile = directory.getCanonicalPath();
		String dir=courseFile.replace('\\', '/')+"/src/main/resources/static/report/";
		
		String filename=file.getOriginalFilename();
		
		//创建要保存文件的路径
      File dirFile = new File(dir+filename);
      if (!dirFile.exists()){
          dirFile.createNewFile();
      }
      try {
          //将文件写入创建的路径
          file.transferTo(dirFile);
          System.out.println("文件保存成功~");
      } catch (IOException e) {
          e.printStackTrace();
      }
      Report report=this.reportRespository.findOne(report_id);
      
      File prefile=new File(dir, report.getReport_name());
      if(prefile.exists()) {
      	Files.delete(prefile.toPath());
      }
      this.reportRespository.delete(report_id);
      
      
      report.setReport_name(filename);
       
		this.reportRespository.save(report);
		
	}

}
