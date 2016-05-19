/**
 * Created by sebadmin on 2016-05-19.
 */
angular
	.module('schedule')
	.factory('alert', function($uibModal) {

		function show(action, event) {
			return $uibModal.open({
				templateUrl: '/app/modules/schedule/modalContent.html',
				controller: function() {
					var vm = this;
					vm.action = action;
					vm.event = event;
				},
				controllerAs: 'vm'
			});
		}

		return {
			show: show
		};

	});
