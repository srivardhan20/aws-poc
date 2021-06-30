package aws.example.rds;

import java.util.List;

import com.amazon.rdsdata.client.RdsDataClient;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rdsdata.AWSRDSData;
import com.amazonaws.services.rdsdata.AWSRDSDataClient;

public class RdsPostgresReadSample {
	public static void main(String[] args) {
		// TODO replace with your rds specific region
		AWSRDSData rdsData = AWSRDSDataClient.builder().withRegion(Regions.AP_SOUTH_1).build();
		// TODO replace with your rds database, resource arn, secret arn
		RdsDataClient rdsDataClient = RdsDataClient.builder().rdsDataService(rdsData).database("database-1")
				.resourceArn("arn:aws:rds:ap-south-1:430066668348:db:database-1")
				.secretArn("arn:aws:secretmanager:ap-south-1:430066668348:secret:dbsecret-dsFEDEE").build();
		// TODO replace with your rds sql query
		List<Object> resultList = rdsDataClient.forSql("Select * from test_base.account").execute()
				.mapToList(Object.class);
		resultList.forEach(result -> System.out.println("result: "+result));
	}
}
