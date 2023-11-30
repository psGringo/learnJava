import {CancelablePromise, Greeting, GreetingsService} from '../../openapi-generated/greeting';

interface IGreetingsApiService {
    sayHello(): CancelablePromise<Greeting | Error>;
}

export const GreetingsApiService: IGreetingsApiService = class GreetingsApiService {

    public static sayHello(): CancelablePromise<Greeting | Error> {
        return new CancelablePromise<Greeting | Error>((resolve, reject) => {
            GreetingsService.sayHello().then((response) => {
                resolve(response);
            }).catch((e) => {
                reject(e);
            })
        });
    }

}
