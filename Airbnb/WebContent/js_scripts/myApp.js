var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope) {
    $scope.carname = "__Volvo__";
    $scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.data = [];
    for (var i=0; i<50; i++) {
        $scope.data.push("Item "+ i);
    }
    $scope.myFunction = function(){
        window.location.href = 'Detailed_Rent.html'
    }
});

app.filter('startFrom', function() {
    return function(input, start) {
        start = +start; //parse to int
        return input.slice(start);
    }
});