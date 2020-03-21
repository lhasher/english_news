userApp.controller("registerCrl",function ($scope,$location,$userService) {
       $scope.test01=function () {
            alert("submit execute!!!")
       }
       /*js中正则的两种写法:
       * 1.直接将正则写入/regex/
       * 2.将正则写入字符串,但是需要转义,例如\w需要写成\\w
       */

       // $scope.usernameregex=/^\w{3,18}/;
       // $scope.usernameregex='^\\w{3,18}';
       // $scope.phoneregex=/^[1]([3-9])[0-9]{9}$/;
    $scope.passwordSaftyPattern = /^.*(?=.{6,16})(?=.*\d)(?=.*[A-Z]{2,})(?=.*[a-z]{2,})(?=.*[!@#$%^&*?\(\)]).*$/
    $scope.submitData=function () {
        //1.先检测密码是否相符
        $scope.check_result = !($scope.user.password === $scope.pw_repeat);
        //2.相符就提交,不相符就提示错误,并阻止提交
        console.info($scope.check_result)
        // alert(this.check_result)
        if (!this.check_result){
            //3.存储用户注册信息
            $userService.saveUser(this.user)
            //4.使用用户邮箱信息给用户发送激活邮件

            // console.info($location.path())
            // console.info($location.url())
            //注册成功跳转登录页面
            $location.path('./pages/login.html');
            // $scope.$apply();
            // $location.url('/home.html')
            // console.info(this.user)
            //页面跳转必须用原生的.location服务只能获取/修改地址栏信息;路由使用可以更安全设定跳转
            // window.location.href=$location.path()
            return true
        }else {
            return false;
        }
    }
    $scope.registerIn=function () {
        this.submitData()
    }

})