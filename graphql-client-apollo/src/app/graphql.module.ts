import { NgModule } from '@angular/core';
import { HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Apollo, ApolloModule } from 'apollo-angular';
import { HttpLinkModule, HttpLink } from 'apollo-angular-link-http';
import { InMemoryCache } from 'apollo-cache-inmemory';

/* const uri = 'http://localhost:8090/graphql'; // <-- add the URL of the GraphQL server here
const secondUri = 'http://localhost:8080/graphql'; // <-- add the URL of the GraphQL server here

export function createApollo(httpLink: HttpLink)  {
  return {
    link: httpLink.create({ uri }),
    cache: new InMemoryCache(),
  };
}


export function createApolloNew(httpLink: HttpLink)  {
  return {
      link: httpLink.create({ uri: secondUri }),
      cache: new InMemoryCache()
  };
} */

@NgModule({
  exports: [ApolloModule, HttpLinkModule],
  providers: [
/*     {
      name:"second",
      provide: APOLLO_OPTIONS,
      useFactory: createApolloNew,
      deps: [HttpLink],
    },
    {
      name:"default",
      provide: APOLLO_OPTIONS,
      useFactory: createApollo,
      deps: [HttpLink],
    }*/
  ], 
})
export class GraphQLModule { 


  private readonly URI1: string = 'http://localhost:8090/graphql';
  private readonly URI2: string = 'http://localhost:8080/graphql';

  constructor(
    apollo: Apollo,
    httpLink: HttpLink
  ) {
    const options1: any = { uri: this.URI1 };
    apollo.createDefault({
      link: httpLink.create(options1),
      cache: new InMemoryCache()
    });

    const options2: any = { uri: this.URI2 };
    apollo.createNamed('endpoint2', {
      link: httpLink.create(options2),
      cache: new InMemoryCache()
    });

    const options3: any = { uri: this.URI1 };
    apollo.createNamed('endpoint3', {
      link: httpLink.create(options3),
      cache: new InMemoryCache()
    });
  }

}
