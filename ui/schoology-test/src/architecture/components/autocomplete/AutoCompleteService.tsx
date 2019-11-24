import { Entity } from '../../service/Entity';

export interface AutoCompleteService<T extends Entity> {
    fieldName: string;
    fieldValue: string;
    methodUrl: string;
    find(inputValue: string, callback: (options: any) => void): Promise<any>;
}

export abstract class AutoCompleteServiceImpl<T extends Entity>
    implements AutoCompleteService<T> {
    abstract fieldName: string;
    abstract fieldValue: string;
    methodUrl: string = '/country/find-by-name';

    find(inputValue: string, callback: (options: any) => void): Promise<any> {
        inputValue = inputValue || '';

        const url = `${process.env.REACT_APP_API_URL}${
            this.methodUrl
        }/${encodeURIComponent(inputValue)}?size=7`;

        return fetch(url)
            .then(response => response.json())
            .then(response =>
                response.map((item: any) => {
                    item.value = item[this.fieldValue];
                    delete item[this.fieldValue];

                    item.label = item[this.fieldName];
                    delete item[this.fieldName];

                    //console.log(item);
                    return item;
                })
            )
            .then(callback, () => callback([]));
    }
}
