public class members {
private String memberId;
    private String name;
    private List<Loan> loans;

    // --- Constructors ---

    /**
     * Creates a member with the given ID and name.
     * Initialises an empty list of loans.
     */
    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.loans = new ArrayList<>();
    }

    // --- Getters and Setters ---

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    // --- Loan list management ---

    /**
     * Adds a loan to this member's list of current loans.
     */
    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    /**
     * Removes a loan from this member's list of current loans.
     */
    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }

    // --- Object overrides ---

    @Override
    public String toString() {
        return "Member{id='" + memberId + "', name='" + name +
               "', activeLoans=" + loans.size() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(memberId, member.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}




    
}