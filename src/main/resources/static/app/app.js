(function(angular) {
  angular.module("myApp.controllers", []);
  angular.module("myApp.services", []);
  angular.module('myApp', [ 'ngResource', 'myApp.controllers', 
                            'myApp.services', 'ngRoute' ])
  .config(function($routeProvider, $httpProvider, $locationProvider) {

		$routeProvider.when('/', {
			templateUrl : 'home.html',
			controller : 'home'
		}).when('/login', {
			templateUrl : 'login.html',
			controller : 'navigation'
		}).when('/myGoals', {
			templateUrl : 'goalControl.html',
			controller : 'GoalController'
		}).when('/newUser', {
			templateUrl : 'addUser.html',
			controller : 'UserController'
		}).otherwise('/');
		
		// use the HTML5 History API
		$locationProvider.html5Mode({
			  enabled: true,
			  requireBase: false
			});
        
		$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

  }).controller(
			'navigation',

			function($rootScope, $scope, $http, $location, $route) {

				$scope.tab = function(route) {
					return $route.current && route === $route.current.controller;
				};

				var authenticate = function(credentials, callback) {

					var headers = credentials ? {
						authorization : "Basic "
								+ btoa(credentials.username + ":"
										+ credentials.password)
					} : {};

					$http.get('user', {
						headers : headers
					}).success(function(data) {
						if (data.name) {
							$rootScope.authenticated = true;
						} else {
							$rootScope.authenticated = false;
						}
						callback && callback($rootScope.authenticated);
					}).error(function() {
						$rootScope.authenticated = false;
						callback && callback(false);
					});

				}

				authenticate();

				$scope.credentials = {};
				$scope.login = function() {
					authenticate($scope.credentials, function(authenticated) {
						if (authenticated) {
							console.log("Login succeeded")
							$location.path("/");
							$scope.error = false;
							$rootScope.authenticated = true;
						} else {
							console.log("Login failed")
							$location.path("/login");
							$scope.error = true;
							$rootScope.authenticated = false;
						}
					})
				};
				$scope.logout = function() {
					$http.post('logout', {}).success(function() {
						$rootScope.authenticated = false;
						$location.path("/");
					}).error(function(data) {
						console.log("Logout failed")
						$rootScope.authenticated = false;
					});
				}

			}).controller('home', function($scope, $http) {
		/*$http.get('/resource/').success(function(data) {
			$scope.greeting = data;
		})*/
	});
}(angular));