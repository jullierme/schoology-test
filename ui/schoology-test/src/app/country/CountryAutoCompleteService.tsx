import { AutoCompleteServiceImpl } from '../../architecture/components/autocomplete/AutoCompleteService';

export class CountryAutoCompleteService extends AutoCompleteServiceImpl {
    api = '/country';
    fieldName = 'name';
    fieldValue = 'abbreviation';
}
