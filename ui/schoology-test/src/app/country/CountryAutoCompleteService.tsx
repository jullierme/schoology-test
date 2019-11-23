import { AutoCompleteServiceImpl } from '../../architecture/service/AutoCompleteService';

export class CountryAutoCompleteService extends AutoCompleteServiceImpl {
  api = '/country';
  fieldName = 'name';
  fieldValue = 'abbreviation';
}
