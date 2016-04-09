package com.fny.reports.service.persistence.dao;

import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fny.reports.commons.entity.BlairDO;
import com.fny.reports.commons.entity.ChunckDO;
import com.fny.reports.commons.entity.NateDO;
@Repository
@Service
@EnableScheduling
public class SheduledJob {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private EmailSenderService emailSenderServiceImpl;

	private static ExecutorService emailExecutorService = Executors.newFixedThreadPool(100);
	private final static Log LOG = LogFactory.getLog(SheduledJob.class);
	
	@Autowired                                              
	VelocityEngine velocityEngine;                                            
	@Value("${reports.main.alerttemplate}")                           
	private String  alerttemplate;
	@Value("${reports.main.counttemplate}")                        
	private String countTemplate;                                          
	@Value("${reports.main.subject}")
	private String subject;
	@Value("${reports.main.sender}")                                          
	private String sender;
	@Value("${reports.main.senderAlert}")
	private String subjectAlert;
	
    @Scheduled(fixedRate =600000)  
	public void checkForUpdates() throws Exception
	{
		Template vTemplate = velocityEngine.getTemplate(countTemplate);
		VelocityContext context = new VelocityContext();
		StringWriter writer = new StringWriter();
        
		vTemplate.merge(context, writer);

		String body = writer.toString();    
		LOG.info("inside sheduled function");
	                                                 
		List<BlairDO> blairlist=blairCheck();
		LOG.info(blairlist);
		if(blairlist!=null)
		{
			
			LOG.info("inside blair");
		   LOG.info(queryforEmailBlair().size());
		   LOG.info(queryforEmailBlair());
		   for(int i=0;i<queryforEmailBlair().size();i++)
		   {
	    		sendReportMail(queryforEmailBlair().get(i), subject, sender, body); 
	    		LOG.info("sending mail");
			   queryforEmailBlair().get(i);  //send email to these ids
		   }
		   
		}
		List<ChunckDO> chuncklist=chunckCheck();
		
		if(chuncklist!=null)
		{
			LOG.info("inside blair");
			LOG.info(queryforEmailChunk().size());
			for(int i=0;i<queryforEmailChunk().size();i++)
			{
				queryforEmailChunk().get(i);  //send email to these Ids
			}
		}
		List<NateDO> natelist=nateCheck();
		if(natelist!=null)
		{
			queryForNateEmail();
			for(int i=0;i<queryForNateEmail().size();i++)
			{
				queryForNateEmail().get(i);  //send email to these ids
			}
		}
	}
		
	public List<BlairDO> blairCheck() throws Exception {
		String sql = "select blair_id,location,skill,status"			
				+ " from Gossiphgirl.blair where created_date between (now()-interval 10 minute) and now()";
        LOG.info(sql);
		List<BlairDO> empList = this.jdbcTemplate.query(sql, new ResultSetExtractor<List<BlairDO>>() {

			public List<BlairDO> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<BlairDO> empList = new ArrayList<BlairDO>();
				while (rs.next()) {

					BlairDO emp = new BlairDO(rs.getInt("blair_id"), rs.getString("location"),
							rs.getString("skill"), 
							rs.getString("status"));
				LOG.info(emp);
				empList.add(emp);
				}
				System.out.println(empList);
				return empList;
			}

		});
       
		return empList;
	}
	
	public List<ChunckDO> chunckCheck() throws Exception {
		String sql = "select chunck_id,location,skill,status"			
				+ " from chunck"+" where created_date between (now()-interval 10 minute) and now()";
        
		List<ChunckDO> empList = this.jdbcTemplate.query(sql, new ResultSetExtractor<List<ChunckDO>>() {

			public List<ChunckDO> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<ChunckDO> empList = new ArrayList<ChunckDO>();
				while (rs.next()) {

					ChunckDO emp = new ChunckDO(rs.getInt("chunck_id"), rs.getString("location"),
							rs.getString("skill"), 
							rs.getString("status"));
				
				empList.add(emp);
				}
				return empList;
			}

		});
       
		return empList;
	}
	public List<NateDO> nateCheck() throws Exception {
		String sql = "select nate_id,location,skill,status"			
				+ " from nate"+" where created_date between (now()-interval 10 minute) and now()";
        
		List<NateDO> empList = this.jdbcTemplate.query(sql, new ResultSetExtractor<List<NateDO>>() {

			public List<NateDO> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<NateDO> empList = new ArrayList<NateDO>();
				while (rs.next()) {

					NateDO emp = new NateDO(rs.getInt("chunck_id"), rs.getString("location"),
							rs.getString("skill"), 
							rs.getString("status"));
				
				empList.add(emp);
				}
				return empList;
			}

		});
       
		return empList;                                
	}
	
	public List<String> queryforEmailChunk() throws SQLException
	{
	String sql="select email from gossip_master inner join subscription on gossip_master.character_id=subscription.character_id";
    
	List<String> eml = this.jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {

		public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<String> eml = new ArrayList<String>();
			while (rs.next()) {
            
				eml.add(rs.getString("email"));
				
			}
			removeDuplicate(eml);
			LOG.info("getting email ids for chunk"+eml);
			return eml;
		}

	});
	return eml;
 }
	public List<String> queryforEmailBlair() throws SQLException
	{
	String sql="select email from gossip_master inner join subscription on gossip_master.character_id=subscription.character_id ";
    LOG.info(sql);
	List<String> eml = this.jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {

		public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<String> eml = new ArrayList<String>();
			while (rs.next()) {
            
				eml.add(rs.getString("email"));
				
			}
			removeDuplicate(eml);
			return eml;
		}

	});
    return eml;			
}
	public List<String> queryForNateEmail() throws SQLException
	{
	String sql="select email from gossip_master inner join subscription on gossip_master.character_id=subscription.character_id";
    
	List<String> eml = this.jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {

		public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<String> eml = new ArrayList<String>();
			while (rs.next()) {
            
				eml.add(rs.getString("email"));
				
			}
			removeDuplicate(eml);
			LOG.info("Getting emails for nate"+eml);
			return eml;
		}

	});
	return eml;

}
	
	private void sendReportMail(final String to, final String subject, final String sender, final String body) {
		@SuppressWarnings("unchecked")
		FutureTask futureTask = new FutureTask(new Callable() { 
			@Override
			public Object call() throws Exception {
				try {
					emailSenderServiceImpl.send(to, subject, sender, body);
					LOG.info("Sending report count mail");
				} catch (Exception ex) {
					LOG.error("Exception = ", ex);
				}
				return null;
			}
		});
		emailExecutorService.execute(futureTask);
	}
	public static <String> void removeDuplicate(List<String> list) {
		LOG.info("removing Duplictes");
		HashSet<String> h = new HashSet<String>(list);
		list.clear();
		list.addAll(h);
	}
}
