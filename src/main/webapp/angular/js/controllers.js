var phonecatApp = angular.module('phonecatApp', []);

phonecatApp.controller('PhoneListCtrl', function ($scope) {
    $scope.phones = [];
    $scope.status = "ok";
    
    $.ajax({
        url: "phones/phones.json",
        async: false
    })
    .done(function( data ) {
        $scope.phones = data;
        $scope.status = "ok";
    })
    .fail(function(status, message, thrown) {
        console.log("ERROR!", status, message, thrown);     
        $scope.status = "error";
    });
});