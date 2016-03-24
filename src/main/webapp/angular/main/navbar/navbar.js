/**
 * Created by lkokhreidze on 3/24/16.
 */

app.directive('navbar', NavbarDirective);

function NavbarDirective() {
    return {
        restrict: 'E',
        templateUrl: '/angular/main/navbar/navbar.html'
    };
}