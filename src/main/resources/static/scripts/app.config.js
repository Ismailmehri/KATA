'use strict';

angular.module('kataApp')
    .config(config);
	function config($urlRouterProvider, $stateProvider) {
            // For any unmatched url, redirect to /login
            $urlRouterProvider.otherwise("/login");
            $stateProvider
                .state('login', {
                    url : '/login',
                    views : {
                        'root' : {
                                templateUrl : 'scripts/user-management/user-management.tp.html',
                                controller: 'UserManagmentController',
                                controllerAs: "userCtrl"
                        }
                    }
                }).state('account', {
                    url : '/account',
                    views : {
                        'root' : {
                                templateUrl : 'scripts/account-management/account-management.tp.html',
                                controller: 'AccountManagmentController',
                                controllerAs: "accountCtrl"
                        }
                    }
                });
	}