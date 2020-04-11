userApp.controller('userCtr', function ($scope,$userService) {
    $scope.login=function (user) {
        $userService.login(user).then(function (data) {
            console.info(data)
            alert(data)
            if (data.$$state.success){
                //登陆成功页面跳转到home页面
                window.location.href='./home.html'
            }else {
                window.location.href='./pages/login.html'
            }
        })
    }

})