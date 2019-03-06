package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.application.domain.model.reservation.TestReservation;
import com.iainhemstock.lendlibrary.application.domain.model.reservation.TestReservation2;
import com.iainhemstock.lendlibrary.application.domain.model.reservation.TestReservation3;
import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.model.reservation.Reservation;
import com.iainhemstock.lendlibrary.domain.model.reservation.ReservationId;
import com.iainhemstock.lendlibrary.domain.model.reservation.ReservationRepository;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class ReservationRepositoryShould extends RepositoryShould {

    protected abstract ReservationRepository getRepository();

    @Test
    public void fetch_all_reservations_of_given_member() {
        Reservation r1 = new Reservation(new ReservationId("id-abcd"), new BookId("id-1234"), new MemberId("id-5678"), new Date());
        Reservation r2 = new Reservation(new ReservationId("id-efgh"), new BookId("id-4267"), new MemberId("id-2068"), new Date());
        Reservation r3 = new Reservation(new ReservationId("id-ijkl"), new BookId("id-8946"), new MemberId("id-5678"), new Date());

        getRepository().add(r1);
        getRepository().add(r2);
        getRepository().add(r3);

        List<Reservation> reservationsByMember = getRepository().findReservationsByMember(new MemberId("id-5678"));

        assertThat(reservationsByMember,
                is(equalTo(List.of(r1, r3))));
    }

    @Test
    public void return_empty_list_when_given_member_has_no_reservations() {
        assertThat(getRepository().findReservationsByMember(new MemberId("id-5678")),
                is(equalTo(Collections.emptyList())));
    }

    @Test
    public void return_all_reservations_of_given_book() {
        Reservation r1 = new Reservation(new ReservationId("id-abcd"), new BookId("id-1234"), new MemberId("id-5678"), new Date());
        Reservation r2 = new Reservation(new ReservationId("id-efgh"), new BookId("id-4267"), new MemberId("id-2068"), new Date());
        Reservation r3 = new Reservation(new ReservationId("id-ijkl"), new BookId("id-4267"), new MemberId("id-6321"), new Date());

        getRepository().add(r1);
        getRepository().add(r2);
        getRepository().add(r3);

        List<Reservation> reservationsByBook = getRepository().findReservationsByBook(new BookId("id-4267"));

        assertThat(reservationsByBook,
                is(equalTo(List.of(r2, r3))));
    }

    @Test
    public void return_empty_list_when_given_book_has_no_reservations() {
        assertThat(getRepository().findReservationsByBook(new BookId("id-4267")),
                is(equalTo(Collections.emptyList())));
    }

    @Test
    public void retrieve_reservation_by_id() {
        Reservation expectedReservation = new TestReservation("id-abcd");
        getRepository().add(expectedReservation);

        Reservation actualItem = getRepository().getById(expectedReservation.getId());

        assertThat(actualItem, is(IsEqual.equalTo(expectedReservation)));
    }

    @Override
    public void return_false_when_repo_does_not_contain_item() {
        assertThat(getRepository().contains(new TestReservation("id-abcd")),
                is(IsEqual.equalTo(false)));
    }

    @Override
    public void return_true_when_repo_contains_item() {
        Reservation repositoryItem = new TestReservation("id-abcd");
        getRepository().add(repositoryItem);

        assertThat(getRepository().contains(repositoryItem),
                is(IsEqual.equalTo(true)));
    }

    @Override
    public void retrieve_all_items_in_repo() {
        Reservation item1 = new TestReservation("id-abcd");
        Reservation item2 = new TestReservation2("id-bcde");
        Reservation item3 = new TestReservation3("id-cdef");

        getRepository().add(item1);
        getRepository().add(item2);
        getRepository().add(item3);

        assertThat(getRepository().getAll(),
                is(IsEqual.equalTo(List.of(item1, item2, item3))));
    }

    @Override
    public void remove_item_from_repo() {
        Reservation item1 = new TestReservation("id-abcd");
        Reservation item2 = new TestReservation2("id-bcde");
        Reservation item3 = new TestReservation3("id-cdef");

        getRepository().add(item1);
        getRepository().add(item2);
        getRepository().add(item3);

        getRepository().remove(item2);

        assertTrue(getRepository().contains(item1));
        assertFalse("repo should not contain item after removal", getRepository().contains(item2));
        assertTrue(getRepository().contains(item3));
    }
}
