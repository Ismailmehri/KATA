'use strict';

angular.module('kataApp').controller('UserManagmentController', UserManagmentController);
UserManagmentController.$inject = ['UserManagementService', '$sessionStorage',
    '$state'];


function UserManagmentController(UserManagementService, $sessionStorage, $state)
{
    var controller = this;
    controller.user = {
        login : '',
        firstName : '',
        lastName : ''
    };
    
    controller.loginError = false;
    controller.signInError = false;
    controller.shouldShowLoginPage = shouldShowLoginPage;
    controller.login = login;
    controller.signIn = signIn;
    
    shouldShowLoginPage();
    
    function shouldShowLoginPage()
    {
        if ($sessionStorage.user) {
            $state.go('account');
        }
    }
    
    function login()
    {
        controller.loginError = false;
        controller.signInError = false;
        UserManagementService.getUser(controller.user.login)
            .then(function(data) {
                $sessionStorage.user = data;
                $state.go('account');
                console.log(data);
        }, function(data) {
            console.log(data);
            controller.loginError = true;
        });
    }
    
    function signIn()
    {
        controller.loginError = false;
        controller.signInError = false;
        UserManagementService.addUser(controller.user)
            .then(function(data) {
                $sessionStorage.user = data;
                $state.go('account');
                console.log(data);
            }, function(data) {
            controller.signInError = true;
            console.log(data);
        });
    }
};
  