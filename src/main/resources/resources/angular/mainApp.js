var myAppModule=angular.module("myApp", [])
myAppModule.controller('userCrl', ['$scope', '$http', 'test',function ($scope, $http,test) {
        $scope.name = 'zhangsan'
        $http.get('/u/one').then(function (response) {
            $scope.user=response.data
            $scope.user.phoneNum=test.doublePhoneNum($scope.user.phoneNum)
            $scope.status=response.status
            console.log(response)
        }).catch(function () {
            alert("error")
        })
    }])
myAppModule.service('test',function () {
    //可以定义若干处理数据的方法
    this.doublePhoneNum=function (phoneNum) {
        return phoneNum*2
    }
})

/*总结:
1.模块:就是一个功能集合,一个模块可以依赖其他模块(继承)
  定义方式:angular.module('模块名',[依赖模块1,依赖模块2,...依赖模块n])
2.控制器:模型数据集中地(获取数据,给视图更新数据)
  定义方法:'模块'.controller('控制器名称',['依赖服务1','依赖服务2',...function(依赖服务1,依赖服务2,...){本控制器内部逻辑}])
  注意:上述依赖的服务来源于框架自带服务.也可以来自本模块所依赖模块中的服务,要使用的服务一定要注入,本模块中也需要其他模块的支持;
3.服务:主要为控制器中模型数据进行逻辑处理的,与Java中一样,可以提前写好,直接调用即可;
  定义方式:'模块'.service('服务名',function(引入的其他服务1,引入的其他服务2,...){
  //提供的各种服务
  this.method1=function(){}
  this.method2=function(){}
  //.....
  })
4.视图模板:就是html页面,绑定了各种指令,模型数据,{{表达式}}
  注意制作一个模板的具体步骤:
  4.1 导入js依赖,具体看依赖哪些模块,服务,控制器分别在哪些js文件中,导入即可
  4.2 标签中引入模块ng-model='模块名',引入控制器ng-controller='控制器名'
  4.3 数据绑定
5. 注意新版的框架ajax服务,格式为
$http.get(url)
.then(function(response){$scope.data=response.data})
.catch(function(){
//发生异常执行的代码...
})
*
* */

