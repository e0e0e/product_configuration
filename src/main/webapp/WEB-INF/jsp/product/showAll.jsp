<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<div>
  <div ng-app="myApp" ng-controller="customersCtrl">


    <div class='row border flex-nowrap' ng-repeat="x in names">
      <div class='col-3' ng-if="$odd" style="background-color:#f1f1f1">{{ x.name }}</div>
      <div class='col-3' ng-if="$even" style="background-color:#ffffff">{{ x.name }}</div>
      <div class='col-3' ng-if="$odd" style="background-color:#f1f1f1">{{ x.id }}</div>
      <div class='col-3' ng-if="$even" style="background-color:#ffffff">{{ x.id }}</div>
      <div class='col-3' ng-repeat="cl in x.configurationList">
  
          <div class='row border' ng-repeat="f in cl.feature">
           {{ f.name}} </div>
      </div>


    </div>

    <script>
      var app = angular.module('myApp', []);
      app.controller('customersCtrl', function ($scope, $http) {
        $http.get("/product/getAll")
          .then(function (response) {
            $scope.names = response.data;
          });
      });
    </script>


  </div>