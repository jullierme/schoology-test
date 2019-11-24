import axios from 'axios';

export interface AutoCompleteService {
    fieldName: string;
    fieldValue: string;
    methodUrl: string;
    find(inputValue: string): Promise<any>;
    getUrl(inputValue: string): string;
}

export abstract class AutoCompleteServiceImpl implements AutoCompleteService {
    abstract fieldName: string;
    abstract fieldValue: string;
    methodUrl: string = '/country/find-by-name';

    getUrl(inputValue: string): string {
        return `${process.env.REACT_APP_API_URL}${
            this.methodUrl
        }/${encodeURIComponent(inputValue)}?size=7`;
    }

    find(inputValue: string): Promise<any> {
        inputValue = inputValue || '';

        return axios
            .get(this.getUrl(inputValue))
            .then(response => response.data)
            .then(response =>
                response.map((item: any) => {
                    item.value = item[this.fieldValue];
                    delete item[this.fieldValue];

                    item.label = item[this.fieldName];
                    delete item[this.fieldName];

                    //console.log(item);
                    return item;
                })
            );
    }
}
