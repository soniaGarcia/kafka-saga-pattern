import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { Apollo } from 'apollo-angular';
import gql from 'graphql-tag';
@Component({
  selector: 'consulta-ordenes',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent2 implements OnInit {

  data: any;

  constructor(private apollo: Apollo) { }

  ngOnInit() {

  }



add(userId: string, productId: string) {
  return this.apollo.use('endpoint2')
    .mutate({
      mutation: gql`
      mutation createOrder($userId: String!, $productId: String!) {
        createOrder(userId: $userId, productId: $productId) {
          id
        }
      }
    `,
      variables: {
        userId,
        productId
      }
    })
    .subscribe(({ data}) => {
      this.data = data;
     alert("Orden registrada con ID: "+this.data.createOrder.id)
      this.apollo.getClient().cache.reset();
    });
}

}


