import {CancelablePromise, Error, NextState, NextStateService} from '../../openapi-generated';

interface IAlienApiService {
    getNextState(command: string, payload?: string): CancelablePromise<NextState | Error>;
}

export const AlienApiService: IAlienApiService = class AlienApiService {
    public static getNextState(command: string, payload?: string): CancelablePromise<NextState | Error> {
        return new CancelablePromise<NextState | Error>((resolve) => {
            NextStateService.nextState(command, payload).then((response) => {
                // eslint-disable-next-line lodash/prefer-lodash-typecheck
                resolve(response);
            }).catch(() => {
                // TODO
            })
        });
    }
}
