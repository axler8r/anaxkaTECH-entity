package tech.anaxka.common.entity;

import static _4axka.common.entity.Builders.addressBuilder;
import static _4axka.common.entity.Builders.emailAddressBuilder;
import static _4axka.common.entity.Builders.telephoneNumberBuilder;
import static tech.anaxka.common.entity.SouthAfricanCitizen.southAfricanCitizenBuilder;

import static _4axka.util.builder.DateTimeBuilder.dateTimeBuilder;

import _4axka.common.entity.Address.AddressType;
import _4axka.common.entity.EmailAddress.EmailAddressType;
import _4axka.common.entity.Person.GenderType;
import _4axka.common.entity.TelephoneNumber.TelephoneNumberType;
import _4axka.common.entity.id.SouthAfricanIdentityDocument;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//~--- JDK imports ------------------------------------------------------------
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.eclipse.persistence.config.PersistenceUnitProperties;

/**
 * Class description
 * <p>
 *
 * @version Enter version here..., 13/12/17
 * @author Enter your name here...
 */
public class EntityJPAPersistenceTests {

    private EntityManager __manager = null;
    private Random __random;
    private final List<String> FAMILIES = new ArrayList<>();
    private final List<String> FEMALES = new ArrayList<>();
    private final List<String> MALES = new ArrayList<>();

    private long ID = 1000;
    private static final String PATH = "C:/Projects/GitHub/Entity/4axka-common-entity/test/_4axka/common/entity/";

    /**
     * Constructs ...
     * <p>
     */
    public EntityJPAPersistenceTests() {
    }

    @BeforeClass
    void setUpTestDataVectors() throws IOException {
        // TODO: fix the paths... Move it to META-INF.
        FAMILIES.addAll(Files.readAllLines(Paths.get(
                PATH + "family.names"),
                StandardCharsets.UTF_8));
        FEMALES.addAll(Files.readAllLines(Paths.get(
                PATH + "female.names"),
                StandardCharsets.UTF_8));
        MALES.addAll(Files.readAllLines(Paths.get(
                PATH + "male.names"),
                StandardCharsets.UTF_8));
    }

    /**
     * Method description
     * <p>
     */
    @BeforeClass(enabled = false)
    void setUpJPAEnvironment() {
        if (__manager == null) {
            __manager = Persistence.createEntityManagerFactory("memoryPU").createEntityManager();
        }

        __manager.setProperty(PersistenceUnitProperties.LOGGING_LOGGER, "JavaLogger");
        __manager.setProperty(PersistenceUnitProperties.LOGGING_LEVEL, "FINE");
        __manager.setProperty("eclipselink.logging.level.sql", "FINE");
        __manager.setProperty("eclipselink.logging.parameters", "true");
    }

    /**
     * Method description
     * <p>
     */
    @Test(enabled = false)
    void testAddPerson() {
//        __manager.getTransaction().begin();
//        __manager.persist(__saffas.iterator().next());
//        __manager.getTransaction().commit();
    }

    /**
     * Method description
     * <p>
     */
    void testFindPerson() {
    }

    /**
     * Method description
     * <p>
     */
    void testModifyPerson() {
    }

    /**
     * Method description
     * <p>
     */
    void testRemovePerson() {
    }

    /**
     * Method description
     * <p>
     */
    @Test(enabled = true)
    public void showGeneratedNames() {
        for (int i_ = 0; i_ < 10; i_++) {
            System.out.println(createSouthAfricanCitizen().toString());
        }
    }

    /**
     * Method description
     * <p>
     *
     * @return
     */
    private SouthAfricanCitizen createSouthAfricanCitizen() {
        return southAfricanCitizenBuilder()
                .setId(nextId())
                .setLegalIdentifier(
                        new SouthAfricanIdentityDocument(
                                "860911 5017 0 8 8",
                                null,
                                dateTimeBuilder()
                                .setYear(2004)
                                .setMonth(11)
                                .setDay(17)
                                .build()))
                .addGivenNames(nextGivenNames(GenderType.MALE))
                .setFamilyName(nextFamilyName())
                .setDateOfBirth(dateTimeBuilder()
                        .setYear(1986)
                        .setMonth(9)
                        .setDay(11)
                        .build())
                .setGender(GenderType.MALE)
                .addEmailAddress(emailAddressBuilder()
                        .setId(nextId())
                        .setType(EmailAddressType.HOME)
                        .setAddress("tshepo.ntsimang@yolo.co.za")
                        .build())
                .addEmailAddress(emailAddressBuilder()
                        .setId(nextId())
                        .setType(EmailAddressType.OFFICE)
                        .setAddress("tkntsimang@bbd.co.za")
                        .build())
                .addTelephoneNumber(telephoneNumberBuilder()
                        .setId(nextId())
                        .setType(TelephoneNumberType.HOME)
                        .setCountryCode("+27")
                        .setAreaCode("11")
                        .setNumber("807 5225")
                        .build())
                .addTelephoneNumber(telephoneNumberBuilder()
                        .setId(nextId())
                        .setType(TelephoneNumberType.MOBILE)
                        .setCountryCode("+27")
                        .setAreaCode("83")
                        .setNumber("456 1230")
                        .build())
                .addAddress(addressBuilder()
                        .setId(nextId())
                        .setType(AddressType.HOME)
                        .setLocation("13 14th Street")
                        .setSuburb("Morningside")
                        .setCity("Sandton")
                        .setRegion("Gauteng")
                        .setCountry("South Africa")
                        .setCode("2196")
                        .build())
                .addAddress(addressBuilder()
                        .setId(nextId())
                        .setType(AddressType.POSTAL)
                        .setLocation("PO Box 1258")
                        .setSuburb("Morningside")
                        .setCity("Sandton")
                        .setRegion("Gauteng")
                        .setCountry("South Africa")
                        .setCode("2096")
                        .build())
                .build();
    }

    private long nextId() {
        return ID++;
    }

    private Iterable<String> nextGivenNames(final GenderType gender) {
        final Set<String> names_ = new LinkedHashSet<>();

        for (int index_ = 0; index_ < 2; index_++) {
            if (gender == GenderType.FEMALE) {
                names_.add(FEMALES.get(new SecureRandom().nextInt(500)));
            } else {
                names_.add(MALES.get(new SecureRandom().nextInt(500)));
            }
        }

        return names_;
    }

    private String nextFamilyName() {
        return FAMILIES.get(new Random().nextInt(500));
    }
}