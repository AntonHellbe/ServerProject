//package org.demo.sebtest;
//
//import org.demo.model.RfidKey;
//import org.demo.model.TimeSamples;
//import org.demo.model.TimeStamp;
//import org.demo.model.User;
//import org.demo.service.databaseservice.MemberService;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//import java.util.HashMap;
//
///**
// * Created by seb on 2016-04-07.
// */
//public class testUserMap {
//	public static HashMap<RfidKey, User> userMap = new HashMap<>();
//
//	public static void main(String[] args) {
//
//		MemberService test = new MemberService();
//		try {
//			userMap = test.loadMember();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		userMap.forEach((s, user) -> {
//			System.out.println("[KEY]: "+s+" [Value]: "+user);
//		});
//
//		RfidKey key = new RfidKey("21");
//		User user = new User("seb", "hero", key, "21");
//		userMap.put(key, user);
//
//		System.out.println(userMap.get(new RfidKey("21")));
//		User tempU = userMap.get(new RfidKey("1"));
//
//		HashMap<RfidKey, ArrayList<TimeStamp>> timeStampMap = new HashMap<>();
//		ArrayList<TimeStamp> stamps = new ArrayList<TimeStamp>();
//
//		Calendar from= new GregorianCalendar(2014, 1, 06,10,00);
//		Calendar to= new GregorianCalendar(2014, 1, 06,16,00);
//
//		stamps.add(new TimeStamp(from,true,tempU.getRfid()));
//		stamps.add(new TimeStamp(to,false,tempU.getRfid()));
//
//		timeStampMap.put(tempU.getRfid(),stamps);
//
//		System.out.println("get all stamps");
//		System.out.println();
//		timeStampMap.forEach((s, stamp) -> {
//			System.out.println("[KEY]: "+s+" [Value]: "+stamp);
//		});
//
//	}
//}
