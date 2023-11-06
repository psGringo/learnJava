import {CancelablePromise, Greeting, GreetingsService} from '../../openapi-generated';

interface IGreetingsApiService {
    sayHello(): CancelablePromise<Greeting | Error>;
}

export const GreetingsApiService: IGreetingsApiService = class GreetingsApiService {

    public static sayHello(): CancelablePromise<Greeting | Error> {
        return new CancelablePromise<Greeting | Error>((resolve) => {
            GreetingsService.sayHello().then((response) => {
                resolve(response);
            }).catch(() => {
                // TODO
            })
        });
    }

}
