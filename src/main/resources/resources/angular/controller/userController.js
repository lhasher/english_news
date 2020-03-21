userApp.controller('userCtr', function ($scope, $http) {
    $http.get('u/one').then(function (response) {
        $scope.user = response.data
        console.log($scope.user)
    });
})