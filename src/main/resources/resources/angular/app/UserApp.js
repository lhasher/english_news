var userApp=angular.module('userApp',['baseApp','ngRoute'])
userApp.config=function ($routeProvider) {
    $routeProvider.when('/home',{
        templateUrl: '../home.html'
    })
}