(function(angular) {
  var UserController = function($scope, UserFactory) {
	  UserFactory.query(function(response) {
      $scope.users = response ? response : [];
    });
    
    $scope.addUser = function(email, password, repassword) {
    	
      if(password == repassword){
          new UserFactory({
              email: email,
              password: password
            }).$save();
            $scope.newUserEmail = "";
            $scope.newUserPassword = "";
            $scope.newUserRePassword = "";
      }else{
    	  $scope.error = "The password does not match";
    	  console.log("Error: The password does not match");
      }

    };
    
    $scope.updateUser = function(user) {
      user.$update();
    };
    
    $scope.deleteUser = function(user) {
      user.$remove(function() {
        $scope.users.splice($scope.users.indexOf(user), 1);
      });
    };
  };
  
  UserController.$inject = ['$scope', 'UserFactory'];
  angular.module("myApp.controllers").controller("UserController", UserController);
}(angular));