import { inject } from "@angular/core";
import { CanActivateFn, CanDeactivateFn, Router } from "@angular/router";
import { RouteService } from "./route.service";
import { FormComponent } from "./components/form.component";

export const canProcced: CanActivateFn =
  (_route, _state) => {
    const routeSvc = inject(RouteService)
    const router = inject(Router)
    console.info('in can proceed')
    if (routeSvc.proceed)
      return true
    return router.parseUrl('/notice')
  } // boolean, UrlTree, Promise<boolean | UrlTree>, Observable<boolean | UrlTree>

export const canLeave: CanDeactivateFn<FormComponent> =
  (comp, _route, _state) => {
    if (comp.hasProcessed)
      return true
   return confirm('You have not saved your data.\nAre you sure you want to leave?')
  } // boolean, UrlTree, Promise<boolean | UrlTree>, Observable<boolean | UrlTree>
