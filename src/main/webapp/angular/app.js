/**
 * Created by lkokhreidze on 3/24/16.
 */

var app = angular
    .module('main', ['ui.router', 'ngResource', 'ui.bootstrap']);

app.config(function ($stateProvider, $locationProvider) {
    $locationProvider.html5Mode(true);

    $stateProvider
        .state('root', {
            abstract: true,
            template: '<ui-view/>',
            url: '/'
        })
        .state('root.tree', {
            url: '',
            controller: 'TreeController as tree',
            templateUrl: '/angular/main/tree/tree.html'
        })
}).run(function () {
    console.log('running...')
});