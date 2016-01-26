var phonecatApp = angular.module('phonecatApp', []);

phonecatApp.controller('PhoneListCtrl', function ($scope) {
    $scope.phones = [];
    
    $.ajax({
        url: "phones/phones.json",
        async: false
    })
    .done(function( data ) {
        $scope.phones = data;
    });
});