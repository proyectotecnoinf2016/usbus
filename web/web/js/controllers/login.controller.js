/**
 * Created by Lucia on 4/26/2016.
 */
(function() {
    'use strict';
    angular.module('usbus').controller('LoginController', LoginController);
    LoginController.$inject = [ '$scope', '$mdDialog', 'LoginUserResource','localStorage'];
    /* @ngInject */
    function LoginController($scope, $mdDialog, LoginUserResource, localStorage) {
        $scope.cancel = cancel;
        $scope.showAlert = showAlert;
		$scope.login = login;

		function login(data) {
			if (data != null && typeof data.username !== 'undefined') {
                data.tenantName = localStorage.getData('tenantName');
                LoginUserResource.Login(data,function(r){
                    console.log(r);
                    showAlert('Exito!','Ha ingresado al sistema de forma exitosa');
                    localStorage.setData('token',r);
                    showAlert(localStorage.getData('token'));
				}, function(r){
					console.log(r);
					showAlert('Error!','Ocurrió un error al procesar su petición');
				});
	    		
    		}
    		else {
    			showAlert('Error', 'Ocurrió un error al procesar su petición');
    		}
		}

        function cancel() {
            $mdDialog.cancel();
        };

        function showAlert(title,content) {
            // Appending dialog to document.body to cover sidenav in docs app
            // Modal dialogs should fully cover application
            // to prevent interaction outside of dialog
            $mdDialog
                .show($mdDialog
                    .alert()
                    .parent(
                        angular.element(document
                            .querySelector('#popupContainer')))
                    .clickOutsideToClose(true)
                    .title(title)
                    .content(content)
                    .ariaLabel('Alert Dialog Demo').ok('Cerrar'));
        };

    }})();
