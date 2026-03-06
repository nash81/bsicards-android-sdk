# BSICARDS Android SDK - Contributing Guide

## Welcome!

We appreciate your interest in contributing to the BSICARDS Android SDK. This guide will help you get started.

## Code of Conduct

Please be respectful and professional in all interactions.

## How to Contribute

### Reporting Bugs

Found a bug? Please create an issue on GitHub with:
- Clear title describing the bug
- Step-by-step reproduction instructions
- Expected vs actual behavior
- Android version and device information
- Log output (if applicable)

### Suggesting Enhancements

Have an idea? Open a GitHub issue with:
- Clear title
- Description of the enhancement
- Use cases and benefits
- Possible implementation approach

### Code Contributions

1. **Fork the repository**
```bash
git clone https://github.com/your-username/bsicards-android-sdk.git
cd bsicards-android-sdk
```

2. **Create a feature branch**
```bash
git checkout -b feature/your-feature-name
```

3. **Make your changes**
- Follow Kotlin naming conventions
- Add proper documentation (KDoc)
- Write unit tests
- Keep commits atomic and descriptive

4. **Test your changes**
```bash
./gradlew test
./gradlew lint
```

5. **Commit and push**
```bash
git commit -m "Add your feature description"
git push origin feature/your-feature-name
```

6. **Create a Pull Request**
- Provide clear description of changes
- Link related issues
- Include before/after examples if applicable

## Coding Standards

### Kotlin Style Guide

- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable names
- Keep functions focused and small
- Add KDoc comments for public APIs

### Example

```kotlin
/**
 * Creates a new MasterCard for the specified user.
 *
 * @param userEmail The user's email address
 * @param nameOnCard The name to print on the card
 * @param pin The 4-digit PIN for the card
 * @return ApiResponse containing card details
 * @throws ValidationException if input is invalid
 */
suspend fun createMastercard(
    userEmail: String,
    nameOnCard: String,
    pin: String
): ApiResponse<CardResponse>
```

### Testing

Write unit tests for new functionality:

```kotlin
import org.junit.Test
import org.junit.Assert.*

class BSICardsClientTest {
    private lateinit var client: BSICardsClient

    @Test
    fun testValidateInput_EmptyEmail_ThrowsException() {
        assertThrows(ValidationException::class.java) {
            // Test code
        }
    }
}
```

## Pull Request Process

1. Update documentation if needed
2. Add tests for new functionality
3. Update CHANGELOG.md
4. Ensure all tests pass
5. Request review from maintainers
6. Address feedback
7. Merge after approval

## Development Setup

### Prerequisites
- Android Studio Arctic Fox or later
- JDK 8+
- Android SDK 21+

### Setup Steps

1. Clone the repository
2. Open in Android Studio
3. Run `./gradlew build`
4. Open bsicards module

### Building

```bash
# Build the library
./gradlew bsicards:build

# Run tests
./gradlew test

# Run lint checks
./gradlew lint

# Generate documentation
./gradlew dokka
```

## Documentation

When contributing:
- Update relevant .md files
- Add/update code examples
- Document any new parameters or return values
- Update API.md if adding new methods

## Commit Messages

Use clear, descriptive commit messages:

```
Add feature: Describe what was added
Fix bug: Describe the bug that was fixed
Update docs: Describe documentation changes
Refactor: Describe the refactoring
```

## Questions?

Feel free to reach out:
- Email: cs@bsigroup.tech
- GitHub Issues: https://github.com/nash81/bsicards-android-sdk/issues

## License

By contributing, you agree your code will be licensed under the MIT License.

Thank you for contributing! 🎉

