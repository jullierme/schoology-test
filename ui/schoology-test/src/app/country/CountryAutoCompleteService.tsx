import { AutoCompleteServiceImpl } from '../../architecture/components/autocomplete/AutoCompleteService';
import { Country } from './Country';

export class CountryAutoCompleteService extends AutoCompleteServiceImpl<Country> {
    api = '/country';
    fieldName = 'name';
    fieldValue = 'abbreviation';
}
