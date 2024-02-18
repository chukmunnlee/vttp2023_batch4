import {inject} from "@angular/core";
import {CanActivateFn, Router} from "@angular/router";
import {WerewolfService} from "./werewolf.service";

export const checkGameState: CanActivateFn =
  (_route, _state) => {
    const werewolfSvc = inject(WerewolfService)
    const router = inject(Router)
    if (werewolfSvc.hasJoined())
      return true
    return router.parseUrl('/')
  }
