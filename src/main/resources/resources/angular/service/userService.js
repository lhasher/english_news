userApp.service('$userService',['$http',function ($http) {
this.saveUser=function save(user) {
console.info(user)
    $http.post('../user/save',user).then(function (response) {
        console.info(response)
        alert("test")
        return response;
    })
}
}])