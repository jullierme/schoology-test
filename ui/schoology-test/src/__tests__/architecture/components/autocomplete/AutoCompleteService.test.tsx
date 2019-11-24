import { AutoCompleteServiceImpl } from '../../../../architecture/components/autocomplete/AutoCompleteService';
import axios from 'axios';

jest.mock('axios');

class TestAcService extends AutoCompleteServiceImpl {
    fieldName = 'name';
    fieldValue = 'abbreviation';
}

it('AutoCompleteService smoke test', () => {
    const service = new TestAcService();

    expect(service).toBeInstanceOf(AutoCompleteServiceImpl);
    expect(service).toHaveProperty('fieldName');
    expect(service).toHaveProperty('fieldValue');
    expect(service).toHaveProperty('methodUrl');

    expect(service).toHaveProperty('getUrl');
    expect(service).toHaveProperty('find');
});

it('should fetch countries', () => {
    const service = new TestAcService();

    const resp = { data: [{ name: 'Brazil', abbreviation: 'BR' }] };
    const countriesFormated = [{ label: 'Brazil', value: 'BR' }];

    axios.get.mockResolvedValue(resp);

    service.find('br').then(data => expect(data).toEqual(countriesFormated));
});

it('should return the correct url when getUrl', () => {
    const service = new TestAcService();
    const expectedUrl = 'http://localhost:8080/country/find-by-name/aaa?size=7';
    const url = service.getUrl('aaa');

    expect(url).toEqual(expectedUrl);
});
