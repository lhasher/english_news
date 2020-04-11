userApp.service('$userService', ['$http', function ($http) {

    //保存用户信息
    this.saveUser = function (user,code) {
        // var postCfg = {
        //     headers: { 'Content-Type': 'x-www-form-urlencoded' },
        //     transformRequest: function (data) {
        //         return $.param(data);
        //     }
        // };
        // console.info(user)
        // alert(code)
        return $http.post('../user/save', {user,code}).then(function (response) {
            console.info(response)
            alert("test")
            return response.data;
        });
    }

    //发送验证码到客户邮箱
    this.sendVerifyCode = function (email) {
        return $http.post('../user/sendVerifyCode', email).then(function (response) {
            console.info(response.data)
            return response.data;
        })
    }

    //登陆操作
    this.login=function (user) {
        return $http.post('../user/login',user).then(function (resp) {
            return resp.data;
        })
    }
}])