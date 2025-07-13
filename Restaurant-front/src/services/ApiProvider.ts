import {environment} from '@env';
import {ApiEndpoints} from './ApiEndpoints';

export class ApiProvider {
  private static readonly baseUrl = environment.apiUrl;

  static getFullUrl(endpoint: string): string {
    return `${this.baseUrl}${endpoint}`;
  }

  static fetch(endpoint: ApiEndpoints, params: { [key: string]: string | number } = {}): string {
    let resolvedEndpoint = this.getFullUrl(endpoint);

    for (const key in params) {
      if (params.hasOwnProperty(key)) {
        resolvedEndpoint = resolvedEndpoint.replace(`{${key}}`, params[key].toString());
      }
    }
    return resolvedEndpoint;
  }
}
