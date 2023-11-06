import React, {useEffect, useState} from "react";
import styles from './Styles.less'
import {GreetingsApiService} from "@/ApiService/GreetingsApiService";
import {Greeting} from "../../../openapi-generated";

export const App: React.FC = () => {

    const [greeting, setGreeting] = useState<Greeting> ({message: 'default'});

    useEffect(() => {
        GreetingsApiService.sayHello().then((result) => {
            setGreeting(result)
        })
    }, [])

    return (
        <div className={styles.app}>
            <div>
                hello world
            </div>

            <div>
                {greeting.message}
            </div>
        </div>)
}