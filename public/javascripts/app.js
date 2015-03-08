var app = angular.module('oroku', [
    'ngMaterial',
    'ngResource'
]);

app.controller('AppCtrl', ['$scope', '$resource', function($scope, $resource) {


    $scope.orokuModel = {
        url: null,
        response: null,
        errorMessage: null,
        status: null
    };

    var oroku = $resource('/page_info/info_for_url',
        {}, //parameters default
        {
            ShredPageInfo: { method: "GET", params: {
                "url": $scope.orokuModel.url
            } }
        });

    $scope.shred = function() {
        $scope.response = null;
        $scope.errorMessage = null;
        $scope.orokuModel.status = 'pending';
        var response = oroku.ShredPageInfo({
            url: $scope.orokuModel.url
        });
        console.log(response);
        response.$promise.then (
            function(data) {
                console.log(data);
                $scope.orokuModel.response = data;
                $scope.orokuModel.status = null;
            },
            function (error) {
                console.error(error);
                $scope.orokuModel.errorMessage = error;
                $scope.orokuModel.status = null;
            }
        );
        //$scope.orokuModel.response = response;

    };

}]);