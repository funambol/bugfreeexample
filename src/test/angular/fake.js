var injector = angular.injector(['ng', 'phonecatApp']);
var scope = injector.get('$rootScope').$new();
var controller = injector.get('$controller');