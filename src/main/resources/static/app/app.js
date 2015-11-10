(function(angular) {
  angular.module("myApp.controllers", []);
  angular.module("myApp.services", []);
  angular.module("myApp", ["ngResource", "myApp.controllers", "myApp.services"]);
  
  
  /*var myApp = angular.module("myApp", ["ngResource", "ngRoute", "myApp.controllers", "myApp.services"]);
  myApp.config(function($routeProvider) {
      $routeProvider
      // route for the index page
      .when('/home', {
          templateUrl : 'index.html',
          controller  : 'ListController'
      })
      
      .when('/item', {
          templateUrl : 'itemsControl.html',
          controller  : 'AppController'
      });
  });*/
 
}(angular));