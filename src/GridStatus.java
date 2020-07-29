/**
 * <b>File Name: </b> <p>GridStatus.java</p>
 * <b>Description: </b>
 * <p>
 * An enum with 3 possible values, X claimed, O claimed, and
 * unclaimed, which model the possible states of a position on a tic tac toe
 * grid. Each possible value has a to string method which prints the relevant
 * symbol.
 * </p>
 *
 * @author nylecm
 */
public enum GridStatus {
    X_CLAIMED {
        public String toString() {
            return "X";
        }
    },
    O_CLAIMED {
        public String toString() {
            return "O";
        }
    },
    UNCLAIMED {
        public String toString() {
            return " ";
        }
    }
}
