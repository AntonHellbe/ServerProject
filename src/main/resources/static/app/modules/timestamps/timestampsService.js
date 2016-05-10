(function() {
	'use strict';

	/**
	 * @ngdoc function
	 * @name app.service:timestampsService
	 * @description
	 * # timestampsService
	 * Service of the app
	 */

  	angular
		.module('timestamps')
		.factory('TimestampsService', Timestamps);
		// Inject your dependencies as .$inject = ['$http', 'someSevide'];
		// function Name ($http, someSevide) {...}

		Timestamps.$inject = ['$http','$resource','$rootScope'];

		function Timestamps ($http,$resource,$rootScope) {
            var tempUrl = $rootScope.ip;
            console.log("tempUrl "+tempUrl);

            return $resource(tempUrl+'/api/time/:id/:stampId', {
                id: '@id',stampId:'@stampId'
            }, {
                get:{
                    method: "GET",
                    isArray: true
                },
                update: {
                    method: "PUT"
                },
                remove: {
                    method: "DELETE"
                },
                save:{
                    method: "POST"
                }
            });

		}

})();

/*

 ////////////////////////////////////////////////////////////////////////
 PasswordEncoder encoder = new BCryptPasswordEncoder();
 Account user2 = new Account("Matt", "Murdock", "user2",
 encoder.encode("pass"),
 AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER)
 );
 user2.setRfidKey(new RfidKey("A448182B"));
 accountRepository.save(user2);
 ////////////////////////////////////////////////////////////////////////
 Account user3 = new Account("John", "Snow", "user3",
 encoder.encode("pass"),
 AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER)
 );
 user3.setRfidKey(new RfidKey("4B79295"));
 accountRepository.save(user3);
 ////////////////////////////////////////////////////////////////////////

 Account user4 = new Account("Lucas", "Hood", "user4",
 encoder.encode("pass"),
 AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER)
 );
 user4.setRfidKey(new RfidKey("247615E"));

 accountRepository.save(user4);

 Account user2 = accountRepository.findByUserName("user2");
 Account user3 = accountRepository.findByUserName("user3");
 Account user4 = accountRepository.findByUserName("user4");




 ArrayList<TimeStamp> calsA = generateStamps(adminUser.getRfidKey());
 ArrayList<TimeStamp> cals1 = generateStamps(defaultUser.getRfidKey());
 ArrayList<TimeStamp> cals2 = generateStamps(user2.getRfidKey());
 ArrayList<TimeStamp> cals3 = generateStamps(user3.getRfidKey());
 ArrayList<TimeStamp> cals4 = generateStamps(user4.getRfidKey());

 calsA.forEach(ts -> timeRepository.save(ts));
 cals1.forEach(ts -> timeRepository.save(ts));
 cals2.forEach(ts -> timeRepository.save(ts));
 cals3.forEach(ts -> timeRepository.save(ts));
 cals4.forEach(ts -> timeRepository.save(ts));


 //		user;C48659EC;1
 //		admin;34915AEC;2
 //		user2;A448182B;3
 //		user3;4B79295;4
 //		user4;247615E;5
 //		Anna;Ek;1C699EB6;6
 //		Carsten;Panduro;8BA8A996;7


 private ArrayList<TimeStamp> generateStamps(RfidKey rfidKey) {
 Random rnd = new Random();
 ArrayList<TimeStamp> cals = new ArrayList<>();
 GregorianCalendar inCal = new GregorianCalendar(), outCal = new GregorianCalendar();
 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


 for (int i = 0; i < 30; i++) {

 int rand = rnd.nextInt(3);
 inCal= new GregorianCalendar(2016, 5, (1 + i), (6 + rand), 0);

 System.out.println("cal "+sdf.format(inCal.getTime()));
 cals.add(new TimeStamp(inCal, true, rfidKey));
 rand = rnd.nextInt(3);
 System.out.println("rand22 "+rand);
 outCal = new GregorianCalendar(2016, 05, 9 + i, (13+rand) , 0);
 cals.add(new TimeStamp(outCal, false, rfidKey));
 }
 System.out.println("rfid "+rfidKey);
 return cals;
 }
 */