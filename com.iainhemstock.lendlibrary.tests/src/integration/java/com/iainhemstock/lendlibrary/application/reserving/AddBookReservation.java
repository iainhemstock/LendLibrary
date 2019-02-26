package com.iainhemstock.lendlibrary.application.reserving;

import com.iainhemstock.lendlibrary.application.cataloging.CatalogingService;
import com.iainhemstock.lendlibrary.application.cataloging.dto.HeadFirstDesignPatternsBookDTO;
import com.iainhemstock.lendlibrary.application.cataloging.impls.CatalogingServiceImpl;
import com.iainhemstock.lendlibrary.application.cataloging.impls.assembler.BookDTOAssembler;
import com.iainhemstock.lendlibrary.application.registering.RegisteringService;
import com.iainhemstock.lendlibrary.application.registering.dto.ColinHartMemberDTO;
import com.iainhemstock.lendlibrary.application.registering.impls.RegisteringServiceImpl;
import com.iainhemstock.lendlibrary.application.registering.impls.assembler.MemberDTOAssembler;
import com.iainhemstock.lendlibrary.application.reserving.dto.ReservationDTO;
import com.iainhemstock.lendlibrary.application.reserving.impls.ReservingServiceImpl;
import com.iainhemstock.lendlibrary.application.reserving.impls.assembler.ReservationDTOAssembler;
import com.iainhemstock.lendlibrary.domain.model.book.BookFactory;
import com.iainhemstock.lendlibrary.domain.model.member.MemberFactory;
import com.iainhemstock.lendlibrary.domain.model.reservation.ReservationId;
import com.iainhemstock.lendlibrary.domain.service.impls.CalendarServiceImpl;
import com.iainhemstock.lendlibrary.domain.shared.Id;
import com.iainhemstock.lendlibrary.infrastructure.persistence.memory.BookRepositoryMemory;
import com.iainhemstock.lendlibrary.infrastructure.persistence.memory.MemberRepositoryMemory;
import com.iainhemstock.lendlibrary.infrastructure.persistence.memory.ReservationRepositoryMemory;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public final class AddBookReservation {

    private ReservingService reservingService;
    private CatalogingService catalogingService;
    private RegisteringService registrationService;
    private String bookId;
    private String memberId;
    private TestableCalendarServiceImpl calendarService;
    private ReservationDTOAssembler reservationDTOAssembler;
    private TestableReservationRepositoryMemory reservationRepository;

    @Before
    public void setUp() throws Exception {
        calendarService = new TestableCalendarServiceImpl();
        reservationDTOAssembler = new ReservationDTOAssembler();
        reservationRepository = new TestableReservationRepositoryMemory();
        reservingService = new ReservingServiceImpl(reservationRepository, calendarService, reservationDTOAssembler);
        catalogingService = new CatalogingServiceImpl(
                new BookRepositoryMemory(), new BookFactory(), new BookDTOAssembler());
        registrationService = new RegisteringServiceImpl(
                new MemberRepositoryMemory(), new MemberFactory(), new MemberDTOAssembler());
        addBookToCatalog();
        registerNewMember();
    }

    @Test
    public void reserve_book_for_member() {
        String actualReservationId = reservingService.reserveBookForMember(bookId, memberId);

        List<ReservationDTO> reservationsByMember = reservingService.fetchReservationsByMember(memberId);
        List<ReservationDTO> reservationsByBook = reservingService.fetchReservationsByBook(bookId);

        ReservationDTO expectedReservationDTO =
                new ReservationDTO(reservationRepository.getLastId().toString(), bookId, memberId, getReservationDate());

        assertThat(actualReservationId,
                is(equalTo(reservationRepository.getLastId().toString())));

        assertThat(reservationsByMember,
                is(equalTo(List.of(expectedReservationDTO))));

        assertThat(reservationsByBook,
                is(equalTo(List.of(expectedReservationDTO))));
    }

    private Date getReservationDate() {
        return calendarService.getLastDate();
    }

    private void addBookToCatalog() {
        bookId = catalogingService.addBookToCatalog(new HeadFirstDesignPatternsBookDTO(null));
    }

    private void registerNewMember() {
        memberId = registrationService.registerNewMember(new ColinHartMemberDTO(null));
    }

    private class TestableReservationRepositoryMemory extends ReservationRepositoryMemory {
        private ReservationId reservationId;

        @Override
        public ReservationId nextId() {
            return reservationId = new ReservationId(UUID.randomUUID().toString().replace("-", ""));
        }

        public ReservationId getLastId() {
            return reservationId;
        }
    }

    private class TestableCalendarServiceImpl extends CalendarServiceImpl {
        private Date today;

        @Override
        public Date now() {
            return today = new Date();
        }

        public Date getLastDate() {
            return today;
        }
    }
}
