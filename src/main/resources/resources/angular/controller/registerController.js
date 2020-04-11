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
    //校验用户名
    $scope.checkUserName=function(username){
        // alert("执行了")
        this.uname_is_usefull=username.$invalid
        // alert(this.uname_is_usefull)
    }
    //校验邮箱
    $scope.checkEmail=function(email){
        this.email_is_usefull=email.$invalid
    }
    //校验手机号
    $scope.checkPhone=function(phone){
        this.phone_is_usefull=phone.$invalid
    }
    //校验密码
    $scope.checkPW=function(password){
        this.password_is_usefull=password.$invalid
    }
    $scope.error_message='OPPS...系统好像出现了故障,请重新发送验证码'
    $scope.is_verifyCodeLoss=false
    $scope.backToNormal=function(){
        this.is_verifyCodeLoss=false
    }
    //发验证码到邮箱,需要先完善好上面的信息且校验无误后才能发送
    $scope.sendVerifyCode=function(email){
        this.is_verifyCodeLoss=false
        let resp = '';
        let isCannotSend = (this.uname_is_usefull === undefined ? true : this.uname_is_usefull) //邮箱有效
            || (this.phone_is_usefull === undefined ? true : this.phone_is_usefull) //手机号有效
            || (this.password_is_usefull === undefined ? true : this.password_is_usefull) //密码满足要求
            || (this.email_is_usefull === undefined ? true : this.email_is_usefull) //用户名有效
            || (this.check_pw_is_Match()) //密码匹配
        if (!isCannotSend){
            //返回结果绑定到变量code中,用于校验用户输入的验证码是否正确
            resp=$userService.sendVerifyCode(email)
            // console.info(resp)
            //resp为一个promise对象,调用多个then方法,then有三种方法,成功回掉,失败回掉,拒绝回掉
            resp.then(function () {
                if (resp.$$state.value.success){
                    $scope.verifyCode=resp.$$state.value.data['verifyCode']
                    // alert($scope.verifyCode)
                    return;
                }
                //如果系统出现未预料的错误,错误提示重新发送验证码
                $scope.is_verifyCodeLoss=true
            })
        }
    }

    $scope.check_pw_is_Match = function(){return $scope.user.password !== $scope.pw_repeat}
    $scope.check_verifyCode_is_Match=function (){
        var result=$scope.verifyCode!==$scope.verifyCode_input
        // alert(result+"->:result")
        if (result){
            // alert("false")
            this.is_verifyCodeLoss=true
            this.error_message='验证码不匹配,请重新输入'
        }
        return result;
    }

    $scope.submitData=function () {
        //1.先检测密码是否相符,验证码是否正确
        //2.相符就提交,不相符就提示错误,并阻止提交
        // alert(this.check_result)
        if (!(this.check_pw_is_Match()||this.check_verifyCode_is_Match())){
            //3.存储用户注册信息
            var resp=$userService.saveUser($scope.user,$scope.verifyCode_input)
            resp.then(function () {
                if (resp.$$state.value.success){
                    //注册成功跳转登录页面
                    $location.path('./pages/login.html');
                    // $location.url('/home.html')
                    // console.info(this.user)
                    //页面跳转必须用原生的.location服务只能获取/修改地址栏信息;路由使用可以更安全设定跳转
                    window.location.href=$location.path()
                    return true;
                }
                this.is_verifyCodeLoss=true
                this.error_message=resp.$$state.value.message
                return false;
            })
        }else {
            return false;
        }
    }
    $scope.registerIn=function () {
        this.submitData()
    }

})