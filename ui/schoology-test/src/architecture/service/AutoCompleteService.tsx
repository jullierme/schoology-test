export interface AutoCompleteService {
  fieldName: string;
  fieldValue: string;
  methodUrl: string;
}

export abstract class AutoCompleteServiceImpl
  implements AutoCompleteService {
  abstract fieldName: string;
  abstract fieldValue: string;
  methodUrl: string = '/country/find-by-name';
}
