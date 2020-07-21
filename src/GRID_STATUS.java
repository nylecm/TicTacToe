/**
 * @author nylecm
 */
public enum GRID_STATUS {
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
