import org.junit.platform.suite.api.*;
import ui.GitHubTest;

@SelectPackages("src/test/ui")
@SelectClasses(GitHubTest.class)
@IncludeTags("regression")
@ExcludeTags("omit")
@Suite
public class Runner {
}
