'use strict';

angular.module('kataApp').controller('AccountManagmentController',
        AccountManagmentController);
AccountManagmentController.$inject = ['AccountManagementService', '$scope',
        '$sessionStorage', '$state'];


function AccountManagmentController(AccountManagementService, $scope,
        $sessionStorage, $state)
{
    var controller = this;
    controller.userLogin = "";
    controller.amount = 0.5;
    controller.depositOrWithdraw = false;
    controller.isUserHasAnAccount = false;
    controller.userHistory = [];
    controller.account = {
        client : $sessionStorage.user,
        balance : 0
    };
    
    controller.shouldShowLoginPage = shouldShowLoginPage;
    controller.createAccount = createAccount;
    controller.getUserAccount = getUserAccount;
    controller.logout = logout;
    controller.changeOperationType = changeOperationType;
    controller.deposit = deposit;
    controller.withdraw = withdraw;
    controller.getUserHistroy = getUserHistroy;
    
    shouldShowLoginPage();
    
    function getUserHistroy()
    {
        AccountManagementService.getUserHistory($sessionStorage.user.id)
                .then(function(data) {
                    console.log(data);
                    controller.userHistory = data;
        });
    }
    
    function deposit()
    {
        AccountManagementService.deposit($sessionStorage.user.id,
            controller.amount).then(function() {
                getUserAccount();
            });
    }
    
    function withdraw()
    {
        AccountManagementService.withdraw($sessionStorage.user.id,
            controller.amount).then(function() {
                getUserAccount();
            });
    }
    
    function changeOperationType()
    {
        controller.depositOrWithdraw = !controller.depositOrWithdraw;
    }
    
    function logout()
    {
        $sessionStorage.user = null;
        $state.go('login');
    }
    
    function shouldShowLoginPage()
    {
        if (!$sessionStorage.user || $sessionStorage.user === null)
        {
           $state.go('login');
        } else {
            controller.userLogin = $sessionStorage.user.login;
            getUserAccount();
        }
    }
    
    function createAccount()
    {   controller.account.client = $sessionStorage.user;
        AccountManagementService.addAccount(controller.account)
            .then(function(data) {
                controller.account = data;
                console.log(data);
        }, function(data) {
            console.log(data);
        });
    }
    
    function getUserAccount()
    {
        AccountManagementService.getUserAccount($sessionStorage.user.id)
            .then(function(data) {
                controller.account = data;
                getUserHistroy()
                console.log(data);
            }, function(data) {
            console.log(data);
        });
    }
};
  