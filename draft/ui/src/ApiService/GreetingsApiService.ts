import {CancelablePromise, Greeting, GreetingsService} from "../../openapi-generated";

interface IGreetingsApiService {
    sayHello(): CancelablePromise<Greeting | Error>
}

export const GreetingsApiService: IGreetingsApiService = class GreetingsApiService {

    public static sayHello(): CancelablePromise<Greeting | Error> {
        return new CancelablePromise<any>((resolve) => {
            GreetingsService.sayHello().then((response) => {
                console.log('here', response);
                resolve(response);
            }).catch((e) => {
                console.log('here error', JSON.stringify(e));
            })
        });
    }

}