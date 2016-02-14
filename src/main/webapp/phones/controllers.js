var phonecatApp = angular.module('phonecatApp', []);

phonecatApp.controller('PhoneListCtrl', function ($scope) {
    $scope.phones = [];
    $scope.status = "ok";
    
    var r = new XMLHttpRequest();
    r.onreadystatechange = function () {
        var DONE = this.DONE || 4;
        if (this.readyState === DONE){
            if (this.status === 200) {
                $scope.phones = JSON.parse(this.responseText);;
                $scope.status = "ok";
            } else {
                $scope.phones = [];
                $scope.status = "error";
            }
        }
    };
    r.open("GET", "phones.json", false);
    r.send();
});